package com.intuit.networthcalculator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;


public class Networth {


    public Networth( ) {
        final  List<AssetMain>NetworthCalculator_List = new ArrayList<>();
        this.networth =  NetworthCalculator_List;
        if (this.getTotalNetworth() == null) {
            this.totalNetworth = new BigDecimal(0.00);
        }else{
            this.totalNetworth = this.getTotalNetworth() ;
        }

    }




    public BigDecimal getTotalNetworth() {
        BigDecimal calulatednetworth = new BigDecimal(0.00);
        if (this.getNetworth().size() > 1){
            var assesrt = this.getNetworth().get(0).getSum();
            var libility = this.getNetworth().get(1).getSum();
           return calulatednetworth = assesrt.subtract(libility);
            //calulatednetworth = assesrt - libility;
        }
        return  calulatednetworth;


    }

        public void setTotalNetworth(BigDecimal totalNetworth) {
        this.totalNetworth = totalNetworth;
    }

    public List<AssetMain> getNetworth() {
        return networth;
    }


    public void setNetworth(List<AssetMain> networth) {
        this.networth = networth;
    }

//    public void addNetworth(AssistTypeList item){
//        List<AssistTypeList> newItem = new ArrayList<>();
//        newItem.add(item);
//        this.networth.add(newItem);
//
//    }

    private BigDecimal totalNetworth;
    private List<AssetMain> networth;








}
