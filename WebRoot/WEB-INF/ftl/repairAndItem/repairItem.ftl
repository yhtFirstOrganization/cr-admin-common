<!--新增维修记录和项目信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html style="height: 90%">
<!--头部-->
<@cc.html_head title="打印维修记录单">
<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
<style type="text/css">
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
</@cc.html_head>
<body>
<div class="pd-20" style="-webkit-print-color-adjust: exact;">
    <!--startprint-->
    <div>
        <input value="秦皇岛东德宝汽车服务有限公司结算单" style="border: none;outline: none;
            font-size: x-large;text-align: center;" class="col-12">
        <form action="" method="post" class="form form-horizontal col-12"
              id="formCar">
            <input type="text" value="${(repairInfo.repairId)!''}" id="repairId"
                   name="repairId" style="display:none"/>
            <div class="mt-10" style="margin-top: 60px">
                <table style="width: 100%;text-align: center;margin-bottom: 10px;float: left">
                    <tbody>
                    <tr class="text-c">
                        <td style="background-color: #a9aea9 !important;width: 10%">维修单号：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${repairInfo.repairNum!''}">
                        </td>
                        <td style="background-color: #a9aea9;width: 10%">车牌号：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(carInfo.licensePlateNum)!''}"></td>
                    </tr>
                    <tr class="text-c" >
                        <td style="background-color: #a9aea9;">客户姓名：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(carInfo.ownerName)!''}"></td>
                        <td style="background-color: #a9aea9;">联系电话：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(carInfo.linkerMobile)!''}"></td>
                    </tr>
                    <tr class="text-c" >
                        <td style="background-color: #a9aea9;">车型：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(carInfo.carType)!''}"></td>
                        <td style="background-color: #a9aea9;">车架号：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(carInfo.carFrameNum)!''}"></td>
                    </tr>
                    <tr class="text-c">
                        <td style="background-color: #a9aea9;">维修顾问：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(repairInfo.serviceAdviser)!''}"></td>
                        <td style="background-color: #a9aea9;">维修时间：</td>
                        <td style="text-align: start"><input class="input-text-none" value="${(repairInfo.repairTimeStr)!''}"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>

    </div>
    <div class="mt-10" style="margin-top: 50px">
        <table class="table-border table-bordered table-hover table-bg table-sort"
               id="repairItem" style="width: 100%;text-align: center;margin: 0 auto;">
            <thead>
            <tr class="text-c">
                <th width="30" id="orderNum">序号</th>
                <!-- <th width="100" id="itemNum">项目编号</th> -->
                <th width="200" id="itemDes">项目描述</th>
                <th width="50" id="itemQuantity">数量</th>
                <th width="80" id="itemPrice">单价</th>
                <th width="60" id="workHoursCost">工时费</th>
                <th width="90" id="itemSum">项目总额</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>


    <form action="" method="post" class="form form-horizontal col-12"
          id="formRepair">
        <div class="row cl">
            <label class="form-label col-2">应收总额：</label>
            <div class="formControls col-2">
                <input type="text" class="input-text-none" value="${repairInfo.repairSum!'0'}" placeholder=""
                       id="repairShouldSum" name="repairShouldSum">
            </div>
            <label class="form-label col-2">实收金额：</label>
            <div class="formControls col-2">
                <input type="text" class="input-text-none" value="${repairInfo.repairActualSum!'0'}" placeholder=""
                       id="repairActualSum" name="repairActualSum">
            </div>
        </div>
        <div style="margin-top: 25px;" class="row cl">
            <label class="form-label col-2">顾问签字：</label>
            <span class="col-2"></span>
            <label class="form-label col-2">客户签字：</label>
        </div>

        <div class="row cl" style="margin-top: 50px">
            <label class="form-label col-1" style="margin-top: 0px;font-weight: 600;">公司：</label>
            <span>秦皇岛东德宝汽车服务有限公司</span>
        </div>
        <div class="row cl"style="margin-top: 0px;">
            <label class="form-label col-1" style="margin-top: 0px;font-weight: 600;">地址：</label>
            <span>秦皇岛市开发区珠江道15号</span>
        </div>
        <div class="row cl" style="margin-top: 0px;">
            <label class="form-label col-1" style="margin-top: 0px;font-weight: 600;">电话：</label>
            <span>0335-8380369</span>
        </div>
    </form>
    <!--endprint-->
    <input class="btn btn-primary radius" type="button" id="print" value="打印"/>
</div>

<script type="text/javascript"
        src="${base}cr-admin/js/repairAndItem/printRepairItem.js"></script>
<script type="text/javascript"
        src="${base}cr-admin/js/jquery.tabletojson.js"></script>
<script type="text/javascript">

</script>
</body>
</html>