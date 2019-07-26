/**************************************************************************************** 
           南京小视科技有限公司           
 ****************************************************************************************/
package com.hhq.gateway.websocket;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * <Description> <br>
 * 
 * @author yangxiaodong<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2019年7月22日 <br>
 */

public class NettyConfig {
    /**
     * 渠道组
     */
    private static ChannelGroup  channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ChannelGroup getChannelGroup(){
        return channelGroup;
    }
}
