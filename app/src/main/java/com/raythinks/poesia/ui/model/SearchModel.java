package com.raythinks.poesia.ui.model;

import java.util.List;

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/10/24 0024<br>.
 * 版本：1.2.0
 */

public class SearchModel {

    /**
     * gushiwens : [{"id":9315,"nameStr":"白雪歌送武判官归京","author":"岑参","chaodai":null,"cont":null,"axing":0,"bxing":0,"cxing":0,"dxing":0,"exing":10875,"type":null,"tag":null,"langsongAuthor":null,"langsongAuthorPY":null,"yizhu":null,"yizhuAuthor":null,"yizhuCankao":null,"yizhuYuanchuang":false,"yizhuIspass":false,"shangIspass":false},{"id":47866,"nameStr":"白头吟","author":"卓文君","chaodai":null,"cont":null,"axing":0,"bxing":0,"cxing":0,"dxing":0,"exing":1837,"type":null,"tag":null,"langsongAuthor":null,"langsongAuthorPY":null,"yizhu":null,"yizhuAuthor":null,"yizhuCankao":null,"yizhuYuanchuang":false,"yizhuIspass":false,"shangIspass":false},{"id":48018,"nameStr":"白马篇","author":"曹植","chaodai":null,"cont":null,"axing":0,"bxing":0,"cxing":0,"dxing":0,"exing":1726,"type":null,"tag":null,"langsongAuthor":null,"langsongAuthorPY":null,"yizhu":null,"yizhuAuthor":null,"yizhuCankao":null,"yizhuYuanchuang":false,"yizhuIspass":false,"shangIspass":false}]
     * mingjus : [{"id":1108,"nameStr":"白日放歌须纵酒，青春作伴好还乡。","classStr":null,"type":null,"shiID":0,"exing":124,"author":null,"shiName":null,"ipStr":null},{"id":3517,"nameStr":"白发悲花落，青云羡鸟飞。","classStr":null,"type":null,"shiID":0,"exing":25,"author":null,"shiName":null,"ipStr":null},{"id":47,"nameStr":"白日依山尽，黄河入海流。","classStr":null,"type":null,"shiID":0,"exing":21,"author":null,"shiName":null,"ipStr":null}]
     * authors : [{"id":665,"nameStr":"白居易","cont":null,"chaodai":null,"pic":null,"likes":4491,"ipStr":null,"creTime":"/Date(-62135596800000)/"},{"id":278,"nameStr":"白朴","cont":null,"chaodai":null,"pic":null,"likes":278,"ipStr":null,"creTime":"/Date(-62135596800000)/"},{"id":1192,"nameStr":"白敏中","cont":null,"chaodai":null,"pic":null,"likes":1,"ipStr":null,"creTime":"/Date(-62135596800000)/"}]
     * typekeys : [{"id":0,"nameStr":"白菊","numInt":0,"shiCount":0},{"id":0,"nameStr":"自白","numInt":0,"shiCount":0}]
     * books : [{"id":217,"nameStr":"白牡丹","author":null,"chaodai":null,"cont":null,"fenlei":null,"axing":0,"bxing":0,"cxing":0,"dxing":0,"exing":2,"ipStr":null,"nameStrKey":"白牡丹","pic":null,"classStr":null,"type":null,"creTime":"/Date(-62135596800000)/"},{"id":183,"nameStr":"太白阴经","author":null,"chaodai":null,"cont":null,"fenlei":null,"axing":0,"bxing":0,"cxing":0,"dxing":0,"exing":3,"ipStr":null,"nameStrKey":"太白阴经 / 神机制敌太白阴经","pic":null,"classStr":null,"type":null,"creTime":"/Date(-62135596800000)/"}]
     */

    private List<GushiwensItem> gushiwens;
    private List<MingjusItem> mingjus;
    private List<AuthorsItem> authors;
    private List<TypekeysModel> typekeys;
    private List<BooksItem> books;

    public void setGushiwens(List<GushiwensItem> gushiwens) {
        this.gushiwens = gushiwens;
    }

    public void setMingjus(List<MingjusItem> mingjus) {
        this.mingjus = mingjus;
    }

    public void setAuthors(List<AuthorsItem> authors) {
        this.authors = authors;
    }

    public void setTypekeys(List<TypekeysModel> typekeys) {
        this.typekeys = typekeys;
    }

    public void setBooks(List<BooksItem> books) {
        this.books = books;
    }

    public List<GushiwensItem> getGushiwens() {
        return gushiwens;
    }

    public List<MingjusItem> getMingjus() {
        return mingjus;
    }

    public List<AuthorsItem> getAuthors() {
        return authors;
    }

    public List<TypekeysModel> getTypekeys() {
        return typekeys;
    }

    public List<BooksItem> getBooks() {
        return books;
    }


    public static class TypekeysModel {
        /**
         * id : 0
         * nameStr : 白菊
         * numInt : 0
         * shiCount : 0
         */

        private int id;
        private String nameStr;
        private int numInt;
        private int shiCount;

        public void setId(int id) {
            this.id = id;
        }

        public void setNameStr(String nameStr) {
            this.nameStr = nameStr;
        }

        public void setNumInt(int numInt) {
            this.numInt = numInt;
        }

        public void setShiCount(int shiCount) {
            this.shiCount = shiCount;
        }

        public int getId() {
            return id;
        }

        public String getNameStr() {
            return nameStr;
        }

        public int getNumInt() {
            return numInt;
        }

        public int getShiCount() {
            return shiCount;
        }
    }
}
