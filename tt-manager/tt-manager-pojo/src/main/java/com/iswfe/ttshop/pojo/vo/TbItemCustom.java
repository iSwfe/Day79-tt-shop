package com.iswfe.ttshop.pojo.vo;

import com.iswfe.ttshop.pojo.po.TbItem;

public class TbItemCustom extends TbItem {
    private String statusView;

    private String catView;

    private String priceView;

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }

    public String getCatView() {
        return catView;
    }

    public void setCatView(String catView) {
        this.catView = catView;
    }

    public String getStatusView() {
        return statusView;
    }

    public void setStatusView(String statusView) {
        this.statusView = statusView;
    }

}