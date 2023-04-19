package com.zjut.Dicom.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    private final static String rootDir = ContentVariable.rootDir;

    /**
     * 向指定目录存放文件
     * @param baseDir 要存放文件夹的目录
     * @param files 文件
     * @return
     */
    public static boolean saveMultiFiles(String baseDir, MultipartFile[] files){
        if(files == null || files.length == 0){
            return true;
        }
        File dcmDir = new File(rootDir, baseDir);
        //存在则进行删除
        if(dcmDir.exists()){
            Arrays.stream(dcmDir.listFiles()).forEach(x->x.delete());
            dcmDir.delete();
        }
        dcmDir.mkdirs();
        for(MultipartFile file: files){
            if(file.getOriginalFilename() == null){
                return false;
            }
            String fileName = file.getOriginalFilename();
            if (fileName.contains("/")){
                fileName = fileName.substring(fileName.lastIndexOf("/"));
            }else if(fileName.contains("\\")){
                fileName = fileName.substring(fileName.lastIndexOf("\\"));
            }
            File dest = new File(dcmDir,fileName);
            try {
                file.transferTo(dest);
            }catch (IllegalStateException | IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 确保目录存在，不存在则创建
     * @param filePath
     */
    private static void makeDir(String filePath){
        if(filePath.lastIndexOf("/") > 0){
            String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
            File dir = new File(dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
        }
    }

    /**
     * 根据一组目录名删除对应的dcm图像
     * @param dirNames dcm目录名列表
     * @return
     */
    public static int deleteDirs(List<String> dirNames){
        int totalDelSuccess = 0;
        for(String name:dirNames){
            Path path = Paths.get(rootDir, name);
            if(!Files.exists(path)){
                continue;
            }
            boolean result = deleteDir(name);
            if (result){
                totalDelSuccess++;
            }
        }
        return totalDelSuccess;
    }

    /**
     * 根据目录名删除
     * @param dirPath  要删除目录名，该目录位与项目存放数据的根目录下ContentVariable.rootDir
     * @return
     */
    public static boolean deleteDir(String dirPath){
        try {
            Files.walkFileTree(Paths.get(rootDir, dirPath),
                    new SimpleFileVisitor<Path>() {
                        // 先去遍历删除文件
                        @Override
                        public FileVisitResult visitFile(Path file,
                                                         BasicFileAttributes attrs) throws IOException {
                            Files.delete(file);
                            return FileVisitResult.CONTINUE;
                        }
                        // 再去遍历删除目录
                        @Override
                        public FileVisitResult postVisitDirectory(Path dir,
                                                                  IOException exc) throws IOException {
                            Files.delete(dir);
                            System.out.printf("文件夹被删除: %s%n", dir);
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static byte[] readFile(String filePath){
        try {
            File file = new File(filePath);
            if(!file.exists()){
                return null;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file.toPath()));
            byte[] bytes = new byte[(int) file.length()];
            bufferedInputStream.read(bytes);
            bufferedInputStream.close();
            return bytes;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static byte[] readFile(File file){
        try {
            if(!file.exists()){
                return null;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file.toPath()));
            byte[] bytes = new byte[(int) file.length()];
            bufferedInputStream.read(bytes);
            bufferedInputStream.close();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
