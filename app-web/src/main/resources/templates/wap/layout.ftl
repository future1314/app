<#assign ctx="${(rca.contextPath)!''}">

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
<#if title??>
    <title>${title!'未知'} - ${appName}</title>
<#else>
    <title>${appName}</title>
</#if>
    <link rel="icon" href="${ctx}/app/images/logo.png" type="image/x-icon"/>
    <meta name="keywords" content="康永敢,疯狂赛车,东方骄子,康帝,开发组,接私活"/>
    <meta name="description" content="不用怀疑，我就是你要找的人！点开看看吧..."/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/font-awesome.min.css"/>

    <link rel="stylesheet" href="${ctx}/app/css/wap.css"/>
    <script src="${ctx}/libs/jquery/jquery-1.8.3.min.js"></script>
    <script>var ctx = '${ctx}';</script>
    <script>
        function IsPC() {
            var userAgentInfo = navigator.userAgent;
            var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
            var flag = true;
            for (var v = 0; v < Agents.length; v++) {
                if (userAgentInfo.indexOf(Agents[v]) > 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        var flag = IsPC(); //true为PC端，false为手机端
        if (flag) {
            window.location.href = "${ctx}/"
        }
    </script>
<@block name="style"/>
</head>

<body>
<@block name="main"/>

<a href="#" class="toTop hidden" id="back-top">
    <i class="fa fa-angle-up"></i>
</a>
<script src="${ctx}/libs/jquery/jquery.form.min.js"></script>
<script src="${ctx}/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}/libs/jquery/jquery.validate.extends.js"></script>
<script src="${ctx}/app/js/wap.js"></script>
<@block name="script"/>
</body>
</html>