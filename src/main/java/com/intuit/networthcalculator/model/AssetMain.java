package com.intuit.networthcalculator.model;
import com.intuit.networthcalculator.model.AssistTypeList;

import java.math.BigDecimal;
import java.util.List;

public  class AssetMain {



    public BigDecimal getSum() {
        var sum = new BigDecimal(0.00);
        for (AssistTypeList element : list) {
            sum = sum.add(element.getSum());
            //sum += element.getSum();
        }
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public List<AssistTypeList> getList() {
        return list;
    }

    public void setList(List<AssistTypeList> list) {
        this.list = list;
    }


    public AssetMain( NetworthType networthType, List<AssistTypeList> list) {
        this.networthType = networthType;
        this.list = list;
        if (this.getSum() == null) {
            this.sum = new BigDecimal(0.00);
        }else{
            this.sum = this.getSum() ;
        }
    }


    public NetworthType getNetworthType() {
        return networthType;
    }

    public void setNetworthType(NetworthType networthType) {
        this.networthType = networthType;
    }

    @Override
    public String toString() {
        return "AssetMain{" +
                "networthType=" + networthType +
                ", list=" + list +
                ", sum=" + sum +
                '}';
    }

    private NetworthType networthType;
    private List<AssistTypeList>  list;
    private BigDecimal sum;

}
