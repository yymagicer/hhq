package com.hhq.common.util;

import com.hhq.common.constant.DigitConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * id生成工具类
 */
public class IdWorkerUtil {



    /**
     * logger <br>
     */
    private static Logger logger = LoggerFactory.getLogger(IdWorkerUtil.class);

    /**
     * H0X_FF <br>
     */
    private static final int H0X_FF = 0xFF;

    /**
     * workerId <br>
     */
    private final long workerId;

    /**
     * epoch 时间起始标记点，作为基准，一般取系统的最近时间<br>
     */
    private final long epoch = 1403854494756L;

    /**
     * workerIdBits 机器标识位数<br>
     */
    private final long workerIdBits = 10L;

    /**
     * maxWorkerId 机器ID最大值: 1023<br>
     */
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;

    /**
     * sequence 并发控制<br>
     */
    private long sequence = 0L;

    /**
     * sequenceBits 毫秒内自增位<br>
     */
    private final long sequenceBits = 12L;

    /**
     * workerIdShift 12<br>
     */
    private final long workerIdShift = this.sequenceBits;

    /**
     * timestampLeftShift 22<br>
     */
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;

    /**
     * sequenceMask 4095,111111111111,12位<br>
     */
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;

    /**
     * lastTimestamp <br>
     */
    private long lastTimestamp = -1L;

    /**
     * Description: <br>
     *
     * @param workerId 参数
     */
    private IdWorkerUtil(long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    /**
     * Description: <br>
     *
     * @author xubin<br>
     * @taskId <br>
     * @return <br>
     * @throws Exception <br>
     */
    public synchronized String nextId() {
        long timestamp = IdWorkerUtil.timeGen();
        if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                // 重新生成timestamp
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            throw new RuntimeException(
                    String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        long id = timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
        return String.valueOf(id);
    }

    /**
     * flowIdWorker <br>
     */
    private static IdWorkerUtil flowIdWorker = new IdWorkerUtil(getworkerHostIp());

    /**
     * Description: <br>
     *
     * @author xubin<br>
     * @taskId <br>
     * @return <br>
     */
    public static IdWorkerUtil getFlowIdWorkerInstance() {
        return flowIdWorker;
    }

    /**
     * Description: 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后<br>
     *
     * @author xubin<br>
     * @taskId <br>
     * @param lastTimestamp 参数
     * @return <br>
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = IdWorkerUtil.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = IdWorkerUtil.timeGen();
        }
        return timestamp;
    }

    /**
     * Description: 获得系统当前毫秒数<br>
     *
     * @author xubin<br>
     * @taskId <br>
     * @return <br>
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * Description: <br>
     *
     * @return int
     */
    public static int getworkerHostIp() {
        try {
            byte[] bytes = InetAddress.getLocalHost().getAddress();
            return Integer.valueOf(bytes[DigitConst.THREE] & H0X_FF);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 1;
        }

    }


}
