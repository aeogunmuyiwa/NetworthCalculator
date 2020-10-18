package com.intuit.networthcalculator.model;

import com.intuit.networthcalculator.datasource.FakeAssetDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetTypeDataAccessService {


    //inject
    private  final FakeAssetDatasource fakeAssetDatasource;

    @Autowired
    public AssetTypeDataAccessService(FakeAssetDatasource fakeAssetDatasource) {
        this.fakeAssetDatasource = fakeAssetDatasource;
    }

    List<AssistTypeList> getAssetTypes(){
        return  fakeAssetDatasource.getAssistTypeList();
    }

    public  List<AssistTypeList> saveAsset(int Category,String name , Double amount){
       return  fakeAssetDatasource.saveAssets(Category,name,amount);

    }
    public void deleteAsset(String Id, int category){
        fakeAssetDatasource.deleteAsset(Id, category);
    }
    public AssetType findById(String id, int Category) {
        return fakeAssetDatasource.findAssetbyId(id, Category);
    }

}
