package com.example.travelexpertsapp.data;

public class Package {
    private int PackageId;
    private String PkgName;
    private String PkgStartDate;
    private String PkgEndDate;
    private String PkgDesc;
    private String PkgBasePrice;
    private String PkgAgencyCommission;

    //--default Constructor--//
    public Package () {}

    //--Constructor--//
    public Package(int packageId, String pkgName, String pkgStartDate, String pkgEndDate, String pkgDesc, String pkgBasePrice, String pkgAgencyCommission) {
        PackageId = packageId;
        PkgName = pkgName;
        PkgStartDate = pkgStartDate;
        PkgEndDate = pkgEndDate;
        PkgDesc = pkgDesc;
        PkgBasePrice = pkgBasePrice;
        PkgAgencyCommission = pkgAgencyCommission;
    }

    //--Getters and Setters--//

    public int getPackageId() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        PackageId = packageId;
    }

    public String getPkgName() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        PkgName = pkgName;
    }

    public String getPkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(String pkgStartDate) {
        PkgStartDate = pkgStartDate;
    }

    public String getPkgEndDate() {
        return PkgEndDate;
    }

    public void setPkgEndDate(String pkgEndDate) {
        PkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        PkgDesc = pkgDesc;
    }

    public String getPkgBasePrice() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(String pkgBasePrice) {
        PkgBasePrice = pkgBasePrice;
    }

    public String getPkgAgencyCommission() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(String pkgAgencyCommission) {
        PkgAgencyCommission = pkgAgencyCommission;
    }

    //--toString--//
    @Override
    public String toString() {
        return  PkgName;
    }

    public String packageDetail() {
        return "Package{" +
                "PackageId=" + PackageId +
                ", PkgName='" + PkgName + '\'' +
                ", PkgStartDate='" + PkgStartDate + '\'' +
                ", PkgEndDate='" + PkgEndDate + '\'' +
                ", PkgDesc='" + PkgDesc + '\'' +
                ", PkgBasePrice='" + PkgBasePrice + '\'' +
                ", PkgAgencyCommission='" + PkgAgencyCommission + '\'' +
                '}';
    }
}//class Package
