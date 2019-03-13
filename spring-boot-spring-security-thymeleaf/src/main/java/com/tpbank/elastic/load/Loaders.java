/*package com.mkyong.elastic.load;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mkyong.elastic.model.Pages;
import com.mkyong.elastic.repository.PagesRepository;


@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    PagesRepository pagesRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){

        operations.putMapping(Pages.class);
        System.out.println("Loading Data");
        pagesRepository.save(getCollection("C:/Users/Acer/Downloads/Microsoft.SkypeApp_kzf8qxf38zg5c!App/All/chovaykm.txt", "cho vay"));
//        pagesRepository.save(getCollection(dirName+"thetindungkm.txt", "tin dung"));
//        pagesRepository.save(getCollection(dirName+"nganhangdtkm.txt", "ngan hang dien tu"));
//        pagesRepository.save(getCollection(dirName+"khachhangdnkm.txt", "khach hang doanh nghiep"));
//        pagesRepository.save(getCollection(dirName+"tietkiemkm.txt", "tiet kiem"));
        System.out.printf("Loading Completed");

    }

    private List<Pages> getData() {
        List<Pages> userses = new ArrayList<>();
        userses.add(new Pages(1L, "chúc mừng năm mới", "tp bank voi nhiu khuyen mai dau xuan nam moi", "khuyen mai", "http://khuyenmai.com"));
        userses.add(new Pages(2L,"cho vay lai suat thap","CHo vay voi lai suat thap chi voi 0 dong", "cho vay", "http://chovay.com"));
        userses.add(new Pages(3L,"mo the tin dung duoc qua","mo the tin dung nhan duoc vourchor 30% tu BHD","the tin dung","http://thetindung.com"));

        return userses;
    }
    
    private List<Pages> getCollection(String colPath,String colTag){
    	FileIO fio = new FileIO();
    	List<Pages> colection = fio.readFile(colPath, colTag);
    	return colection;
    }
   
}*/