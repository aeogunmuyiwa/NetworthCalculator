package com.intuit.networthcalculator.datasource;

import com.intuit.networthcalculator.model.AssetType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeAssetDatasource {
    private static final List<AssetType> NetWorthAssets = new ArrayList<>();
    static {
        NetWorthAssets.add(new AssetType( UUID.randomUUID(),"DD", (double) 1000));
        NetWorthAssets.add(new AssetType( UUID.randomUUID(), "DD", (double) 1000));
        NetWorthAssets.add(new AssetType( UUID.randomUUID(), "DD", (double) 1000));
        NetWorthAssets.add(new AssetType( UUID.randomUUID(),"DD", (double) 1000));

    }

    public List<AssetType> getAssetTypes(){
        return  NetWorthAssets;
    }
    public List<AssetType> saveAsset(String name , Double amount){
        AssetType newAsset = new AssetType();
        newAsset.setAssesId(UUID.randomUUID());
        newAsset.setAmount(amount);
        newAsset.setName(name);
        NetWorthAssets.add(newAsset);
        return  NetWorthAssets;

    }
    public AssetType findById(String id){

        for (AssetType element : NetWorthAssets){

            if (element.getAssetIdString().compareTo(id) == 0 ){
                return element;
            }
        }


        return null;
    }

//    public void deleteAsset(String id){
//        for (AssetType element : NetWorthAssets){
//            if (element.getAssitId().toString() == id) {
//                NetWorthAssets.remove(element);
//            }
//        }
//    }
}
