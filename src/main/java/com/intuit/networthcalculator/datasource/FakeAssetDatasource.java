package com.intuit.networthcalculator.datasource;

import com.intuit.networthcalculator.model.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeAssetDatasource {
    private static final List<AssetType> NetWorthAssets_cash = new ArrayList<>();
    private static final List<AssetType> NetWorthAssets_LongTerm = new ArrayList<>();
    private static final List<AssetType> NetWorthLiability_short = new ArrayList<>();
    private static final List<AssetType> NetWorthLiability_LongTerm = new ArrayList<>();

    private static final  List<AssistTypeList> assistList = new ArrayList<>();
    private static final  List<AssistTypeList> LiabilityList = new ArrayList<>();

    private static final AssetMain assetManager = new AssetMain(NetworthType.asset,assistList);
    private static final AssetMain LiabilityManager = new AssetMain(NetworthType.liability,LiabilityList);



    private static final  Networth NetworthCalculator = new Networth();
   // private static final  List<List<AssistTypeList>>NetworthCalculator_List = new ArrayList<>();


    static {

        NetWorthAssets_cash.add(new AssetType( UUID.randomUUID(),"DD",  new BigDecimal(1000)));
        NetWorthAssets_cash.add(new AssetType( UUID.randomUUID(),"DD", new BigDecimal(1000)));
        NetWorthLiability_short.add(new AssetType( UUID.randomUUID(),"DD", new BigDecimal(1000)));

        var CashandInvestments = new AssistTypeList("Cash and Investments", NetWorthAssets_cash, NetworthType.asset);
        var LongTermAssets = new AssistTypeList("Long Term Assets", NetWorthAssets_LongTerm,NetworthType.asset);
        var ShortTermLiabilties = new AssistTypeList("Short Term Liabilties",NetWorthLiability_short,NetworthType.liability);
        var LongTermDebt = new AssistTypeList("Long Term Debt",NetWorthLiability_LongTerm ,NetworthType.liability);

        assistList.add(CashandInvestments);
        assistList.add(LongTermAssets);

        LiabilityList.add(ShortTermLiabilties);
        LiabilityList.add(LongTermDebt);
//
//        NetworthCalculator_List.add(assistList);
//        NetworthCalculator_List.add(LiabilityList);



        NetworthCalculator.getNetworth().add(assetManager);
        NetworthCalculator.getNetworth().add(LiabilityManager);

//        NetworthCalculator.getNetworth().add(assistList);
//        NetworthCalculator.getNetworth().add(LiabilityList);

       // NetworthCalculator.setNetworth(NetworthCalculator_List);

//        NetworthCalculator.addNetworth(CashandInvestments);
//        NetworthCalculator.addNetworth(LongTermAssets);
//        NetworthCalculator.addNetworth(ShortTermLiabilties);
//        NetworthCalculator.addNetworth(LongTermDebt);

    }

/**
    returns the list [ [AssistTypeList] [AssistTypeList] ]
    where first element is asset and second element is liability
* */
   public List<AssetMain> getNetworthCalculator() {
        return NetworthCalculator.getNetworth();
   }
    public Networth getNetworthData() {
        return NetworthCalculator;
    }

    /**
     * Param : type : int - location in db 0 = asset, 1 = liability
     *          category : int - AssistTypeList position - index tracking the type of asset / liability
     *          item : AssetType - contains asset detail
     */
//   public void SaveNetworth(int type,int category  , AssetType item){
//        var networth = getNetworthCalculator();
//        networth.get(type).get(category).getAssetTypeList().add(item);
//   }

    /**
     * find liabilities by element id and category
     * returns AssetType
     * */
    public AssetType findLiablitytbyId(String id, int Category){
        var category =  getLiabilityList().get(Category).getAssetTypeList();
        for (AssetType element : category){

            if (element.getAssetIdString().compareTo(id) == 0 ){
                return element;
            }
        }
        return null;
    }


    /**
     * deletes Liability by element id and category
     * delete Liability from NetworthCalculator
     * delete Liability from getLiabilityList
     * */
    public void deleteLiability(String id, int category){
        var Category =  getLiabilityList().get(category).getAssetTypeList();

        var element = Category.iterator();
        while (element.hasNext()){
            AssetType Element = element.next();
            if (Element.getAssetIdString().compareTo(id) == 0) {
                element.remove();
            }
        }
    }

    /**
     * return list of Liabilities type
     * */

    public  List<AssistTypeList> getLiabilityList() {
        return LiabilityList;
    }


    /**
     * save Liabilty by category name and amount
     * returns List of Liabilities
     * */
    public List<AssistTypeList> saveLiability(int Category, String name , BigDecimal amount ){
        var category =  getLiabilityList().get(Category);
        var item =  saveAssetBuilder(name, amount);
        category.getAssetTypeList().add(item);
       // SaveNetworth(1,Category,item);
        return getLiabilityList();
    }

    /**
     * util function : builds AssetType from name and amount
     * AssetType - name
     * AssetType - amount
     * AssetType - assetId
     * */

    public AssetType saveAssetBuilder(String name , BigDecimal amount){
        AssetType newAsset = new AssetType();
        newAsset.setAssesId(UUID.randomUUID());
        newAsset.setAmount(amount);
        newAsset.setName(name);
        return  newAsset;
    }



    /**
     * deletes asset by element id and category
     * delete asset from NetworthCalculator
     * delete asset from getAssistTypeList
     * */
    public void deleteAsset(String id, int category){
        var Category =  getAssistTypeList().get(category).getAssetTypeList();
        var deleteNetworthAsset = getNetworthCalculator().get(0).getList().get(category).getAssetTypeList();

        var element = Category.iterator();
        while (element.hasNext()){
            AssetType Element = element.next();
            if (Element.getAssetIdString().compareTo(id) == 0) {
                element.remove();

            }


        }
    }

    /**
     * find asset by element id and category
     * returns AssetType
     * */
    public AssetType findAssetbyId(String id, int Category){
        var category =  getAssistTypeList().get(Category).getAssetTypeList();
        for (AssetType element : category){

            if (element.getAssetIdString().compareTo(id) == 0 ){
                return element;
            }
        }
        return null;

    }
    /**
     * return list of assets
     * */

    public  List<AssistTypeList> getAssistTypeList() {
        return assistList;
    }

    /**
     * save asset by category name and amount
     * returns List of asset
     * */
    public List<AssistTypeList> saveAssets(int Category, String name , BigDecimal amount ){
        var category =  getAssistTypeList().get(Category);
        var item = saveAssetBuilder(name, amount);
        category.getAssetTypeList().add(item);
        //SaveNetworth(0,Category,item);
        return getAssistTypeList();
    }


}

