//package com.hhq.gateway.File;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.hhq.common.util.JsonUtils;
//import com.hhq.gateway.constant.CommonConstants;
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@RestController
//@RequestMapping("/file")
//public class UploadFileController {
//    private Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);
//    /**
//     * 文件上传路径
//     */
//    @Value("${push_images_upload}")
//    public String PUSH_IMAGES_UPLOAD;
//
//    /**
//     * 上传文件
//     *
//     * @param file
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//                + request.getContextPath() + "/";
//        if (file.isEmpty()) {
//            return JsonUtils.getFail(CommonConstants.FILE_EMPTY, CommonConstants.FILE_EMPTY_MSG);
//        } else {
//            String filePath = PUSH_IMAGES_UPLOAD;
//            System.out.println("文件长度: " + file.getSize());
//            System.out.println("文件类型: " + file.getContentType());
//            System.out.println("文件名称: " + file.getName());
//            System.out.println("文件原名: " + file.getOriginalFilename());
//            System.out.println("========================================");
//            String filename = Long.toString(System.currentTimeMillis())
//                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, file.getOriginalFilename()));
//            File newfile = new File(filePath + "/" + filename);
//            File oldfile = new File(filePath + "/" + file.getOriginalFilename());
//            oldfile.renameTo(newfile);
//            System.out.println("文件现名: " + filename);
//            System.out.println("========================================");
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("title", filename);
//            map.put("src", basePath + "download/" + filename.substring(0, filename.lastIndexOf(".")) + "/"
//                    + filename.substring(filename.lastIndexOf(".") + 1));
//            return JsonUtils.getSucc(map);
//        }
//    }
//
//    /**
//     * 下载文件
//     *
//     * @param fileName
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "/download")
//    public void download(@RequestParam("fileName") String fileName, HttpServletRequest request,
//                         HttpServletResponse response) {
//        LOGGER.info("下载文件名{}: ", fileName);
//        String fileUrl = PUSH_IMAGES_UPLOAD + fileName;
//        File file = new File(fileUrl);
//        if (file.exists()) {
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
//            byte[] buffer = new byte[1024];
//            try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis); OutputStream os = response.getOutputStream()) {
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    os.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//                System.out.println("success");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
