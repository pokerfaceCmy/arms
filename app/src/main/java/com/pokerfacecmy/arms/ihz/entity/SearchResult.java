package com.pokerfacecmy.arms.ihz.entity;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 17:07
 * @description com.pokerfacecmy.arms.ihz.entity
 * @email cheng.meng.yuan@qq.com
 */
public class SearchResult {
    /**
     * indexName : 尿免疫固定电泳
     * introduction :
     * 尿免疫固定电泳又称尿蛋白免疫固定电泳，指对尿液中各种蛋白成分进行分离，区分尿蛋白的类型，用于检测导致肾脏损伤的特异性蛋白，以及评价肾脏损伤的部位、程度等。该检查方法简单、快速、图像清晰、易于解释结果、敏感性高。
     * clinical : 1.单克隆免疫球蛋白增殖病以单一克隆的浆细胞过度增高为特征，常导致某种免疫球蛋白或免疫球蛋白亚单位大量合成而其他正常免疫球蛋白水平下降。IFE
     * 能够确定这些蛋白质的单克隆属性。2
     * .本周氏蛋白和游离轻链病本周氏蛋白是没有与免疫球蛋白分子中重链结合的单克隆κ或λ轻链。免疫固定电泳可确定本周氏蛋白的存在形式。轻链病是仅产生κ或λ单克隆轻链，在尿中称为本周氏蛋白的疾病，轻链病包括10%～15%的单克隆免疫球蛋白病，多出现在IgG骨髓瘤（60%）和IgA骨髓瘤（16%）疾病中。3.重链病以免疫球蛋白重链部分存在的单克隆蛋白为特征。4.多克隆免疫球蛋白病主要为γ蛋白区宽的条带，多见于炎症或感染和胶原病。5.肾脏病变的来源分析尿免疫蛋白固定电泳亦可协助临床判断肾脏的主要损伤部位。根据尿免疫蛋白固定电泳，以判断尿蛋白为选择性或非选择性，肾脏病变是在肾小球还是在肾小管，对指导治疗和判断预后有一定的价值。
     * range :
     * notice :
     * detection : 免疫固定电泳（immunofixationelectrophoresis，IFE）是一种包括琼脂凝胶蛋白电泳和免疫沉淀两个过程的操作。可检测IgG
     * 、IgA、IgM，κ轻链、λ轻链及κ游离轻链、λ游离轻链。原理是将尿液在琼脂平板上作区带电泳，分离后其上覆盖抗血清滤纸，滤纸分别含抗κ轻链、抗l
     * 轻链，或抗各类重链抗血清，当抗体与某区带中的单克隆Ig结合，可形成免疫复合物沉淀，即固定，再通过漂洗与染色，呈现浓而窄的着色区带，即可判别单Ig的轻链和重链的类别。
     * preExamine :
     * labExamine :
     * labMethod :
     */

    private String indexName;
    private String introduction;
    private String clinical;
    private String range;
    private String notice;
    private String detection;
    private String preExamine;
    private String labExamine;
    private String labMethod;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getClinical() {
        return clinical;
    }

    public void setClinical(String clinical) {
        this.clinical = clinical;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDetection() {
        return detection;
    }

    public void setDetection(String detection) {
        this.detection = detection;
    }

    public String getPreExamine() {
        return preExamine;
    }

    public void setPreExamine(String preExamine) {
        this.preExamine = preExamine;
    }

    public String getLabExamine() {
        return labExamine;
    }

    public void setLabExamine(String labExamine) {
        this.labExamine = labExamine;
    }

    public String getLabMethod() {
        return labMethod;
    }

    public void setLabMethod(String labMethod) {
        this.labMethod = labMethod;
    }
}
