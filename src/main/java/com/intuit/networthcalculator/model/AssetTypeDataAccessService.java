package com.intuit.networthcalculator.model;

import com.intuit.networthcalculator.datasource.FakeAssetDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AssetTypeDataAccessService {

    //inject
    private  final FakeAssetDatasource fakeAssetDatasource;

    @Autowired
    public AssetTypeDataAccessService(FakeAssetDatasource fakeAssetDatasource) {
        this.fakeAssetDatasource = fakeAssetDatasource;
    }

    /**
     * Get all data from datasource
     * */
    Networth getNetworthData (){
        return fakeAssetDatasource.getNetworthData();
    }

    /**
     * Get totalNetworth from datasource
     * */
    List<AssetMain> getNetworth(){
        return fakeAssetDatasource.getNetworthCalculator();
    }

    /**
     * Get liability from datasource
     * */
    List<AssistTypeList> getLiabiltyTypes(){
        return fakeAssetDatasource.getLiabilityList();
    }
    /**
     * Save liability tp datasource
     * */
    public  List<AssistTypeList> saveLiability(int Category, String name , BigDecimal amount){
        return fakeAssetDatasource.saveLiability(Category,name,amount);
    }

    /**
     * Delete liability from datasource
     * */
    public void deleteLiability(String Id, int category){
        fakeAssetDatasource.deleteLiability(Id,category);
    }
    /**
     * find liability from datasource
     * */
    public AssetType findLiabilityById(String id, int Category) {
        return fakeAssetDatasource.findLiablitytbyId(id,Category);
    }




    /**
     * get asset from datasource
     * */
    List<AssistTypeList> getAssetTypes(){
        return  fakeAssetDatasource.getAssistTypeList();
    }
    /**
     * Save asset to datasource
     * */
    public  List<AssistTypeList> saveAsset(int Category, String name , BigDecimal amount){
       return  fakeAssetDatasource.saveAssets(Category,name,amount);

    }
    /**
     * Delete asset from datasource
     * */
    public void deleteAsset(String Id, int category){
        fakeAssetDatasource.deleteAsset(Id, category);
    }
    /**
     * find asset from datasource
     * */
    public AssetType findById(String id, int Category) {
        return fakeAssetDatasource.findAssetbyId(id, Category);
    }

}
