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

    List<AssetType> getAssetTypes(){

        return  fakeAssetDatasource.getAssetTypes();
    }
    public List<AssetType> saveAsset(String name , Double amount){
       return  fakeAssetDatasource.saveAsset(name,amount);

    }
//    public void deleteAsset(String Id){
//        fakeAssetDatasource.deleteAsset(Id);
//    }
    public AssetType findById(String id) {
        return fakeAssetDatasource.findById(id);
    }

}
