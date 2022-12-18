package com.jureumuing.nolhac.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String uploadFileExemple(MultipartFile file, String dataType){
        //type은 해당 파일이

        String filePath=null;
        //파일 비었는지 확인
        if(file!=null){
                String originalName = file.getOriginalFilename();
                //파일 경로 말고 파일 이름만 가져오기
                String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
                //UUID: 범용 고유 식별자
                String uuid = UUID.randomUUID().toString();
                //lastIndexOf("__")앞까지가 uuid임. 그 이후는 서버로 전송된 상태의 파일 이름
                String savefileName = "challenge_example"+ "/" +dataType+"/"+ uuid + "__" + fileName;

                try {
                    ObjectMetadata objMeta = new ObjectMetadata();
                    //파일 사이즈 읽어와 objectmetadata 세팅
                    objMeta.setContentLength(file.getInputStream().available());
                    if(objMeta.getContentLength()==0){
                        return filePath;
                    }
                    //파일 s3에 저장
                    amazonS3.putObject(bucket, savefileName, file.getInputStream(), objMeta);
                    //파일의 저장 위치 리스트에 추가
                    filePath=amazonS3.getUrl(bucket,savefileName).toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return filePath;
    }

    public String uploadFileProving(MultipartFile file, String dataType){
        //type은 해당 파일이 challenge

        String filePath=null;
        //파일 비었는지 확인
        if(file!=null){
            String originalName = file.getOriginalFilename();
            //파일 경로 말고 파일 이름만 가져오기
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            //UUID: 범용 고유 식별자
            String uuid = UUID.randomUUID().toString();
            //lastIndexOf("__")앞까지가 uuid임. 그 이후는 서버로 전송된 상태의 파일 이름
            String savefileName = "challenge_proving"+ "/" +dataType+"/"+ uuid + "__" + fileName;

            try {
                ObjectMetadata objMeta = new ObjectMetadata();
                //파일 사이즈 읽어와 objectmetadata 세팅
                objMeta.setContentLength(file.getInputStream().available());
                if(objMeta.getContentLength()==0){
                    return filePath;
                }
                //파일 s3에 저장
                amazonS3.putObject(bucket, savefileName, file.getInputStream(), objMeta);
                //파일의 저장 위치 리스트에 추가
                filePath=amazonS3.getUrl(bucket,savefileName).toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
}
