package com.intuit.networthcalculator.datasource;

import com.intuit.networthcalculator.model.AssetType;
import com.intuit.networthcalculator.model.AssistTypeList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeAssetDatasource {
    private static final List<AssetType> NetWorthAssets_cash = new ArrayList<>();
    private static final List<AssetType> NetWorthAssets_LongTerm = new ArrayList<>();
    private static final List<AssetType> NetWorthLiability_short = new ArrayList<>();
    private static final List<AssetType> NetWorthLiability_LongTerm = new ArrayList<>();

    private static final  List<AssistTypeList> AssistTypeList = new ArrayList<>();

    static {
//        NetWorthAssets.add(new AssetType( UUID.randomUUID(),"DD", (double) 1000));
//        NetWorthAssets.add(new AssetType( UUID.randomUUID(), "DD", (double) 1000));
//        NetWorthAssets.add(new AssetType( UUID.randomUUID(), "DD", (double) 1000));
//        NetWorthAssets.add(new AssetType( UUID.randomUUID(),"DD", (double) 1000));
        AssistTypeList.add(new AssistTypeList("Cash and Investments", NetWorthAssets_cash));
        AssistTypeList.add(new AssistTypeList("Long Term Assets", NetWorthAssets_LongTerm));
    }
    public  List<AssistTypeList> getAssistTypeList() {
        return AssistTypeList;
    }

//    public List<AssetType> getAssetTypes(){
//        return  NetWorthAssets;
//    }
//    public List<AssetType> saveAsset(String name , Double amount){
//        AssetType newAsset = new AssetType();
//        newAsset.setAssesId(UUID.randomUUID());
//        newAsset.setAmount(amount);
//        newAsset.setName(name);
//        return  NetWorthAssets;
//
//    }

    public List<AssistTypeList> saveAssets(int Category, String name , Double amount ){
       var category =  getAssistTypeList().get(Category);
       category.getAssetTypeList().add(saveAssetBuilder(name, amount));
       return getAssistTypeList();
    }
    public AssetType saveAssetBuilder(String name , Double amount){
        AssetType newAsset = new AssetType();
        newAsset.setAssesId(UUID.randomUUID());
        newAsset.setAmount(amount);
        newAsset.setName(name);
        return  newAsset;
    }
    public AssetType findAssetbyId(String id, int Category){
        var category =  getAssistTypeList().get(Category).getAssetTypeList();
        for (AssetType element : category){

            if (element.getAssetIdString().compareTo(id) == 0 ){
                return element;
            }
        }
        return null;

    }

//    public AssetType findById(String id){
//
//        for (AssetType element : NetWorthAssets){
//
//            if (element.getAssetIdString().compareTo(id) == 0 ){
//                return element;
//            }
//        }
//
//
//        return null;
//    }

    public void deleteAsset(String id, int category){
        var Category =  getAssistTypeList().get(category).getAssetTypeList();
        for (AssetType element : Category){
            if (element.getAssetIdString().compareTo(id) == 0) {
                Category.remove(element);
            }
        }

    }
}

