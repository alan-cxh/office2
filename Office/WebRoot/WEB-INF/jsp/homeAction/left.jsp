<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>导航菜单</title>
		<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf"%>
		<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
		<script type="text/javascript">
			function menuClick( menu ){
				$(".MenuLevel2").not($(menu).next()).hide();  //点击展开菜单时，其他展开的菜单自动收上
				$(menu).next().toggle();//点击菜单时显示或者隐藏子菜单
			}
		</script>
	</head>
	<body style="margin: 0">
		<div id="Menu" data-role="page">
			<ul id="MenuUl">
				<%-- 显示一级菜单 --%>
				<s:iterator value="#application.topPrivilegeList">
					<s:if test="#session.user.hasPrivilegeByName(name)">
					<li class="level1">
						<div onClick="menuClick(this);" class="level1Style">
							<!-- 先不用Id来控制图标 -->
							<%-- <img src="style/images/MenuIcon/${id}.gif" class="Icon" /> ${name} --%>
							<img src="${pageContext.request.contextPath}/style/images/MenuIcon/${icon}" class="Icon" /> ${name}
						</div>
						<ul class="MenuLevel2" style="display: none">
							<%-- 显示二级菜单 --%>
							<s:iterator value="children">
								<s:if test="#session.user.hasPrivilegeByName(name)">
								<li class="level2">
									<div class="level2Style">
										<img src="style/images/MenuIcon/menu_arrow_single.gif" />
										<a target="right" href="${pageContext.request.contextPath}${url}.action"> ${name}</a>
									</div>
								</li>
								</s:if>
							</s:iterator>
						</ul> 
					</li>
					</s:if>
				</s:iterator>
				
				
				<!-- 常用工具,通过静态页面来写 -->
				<li class="level1" >
					<div onClick="menuClick(this);" class="level1Style">
						<img src="style/images/MenuIcon/tools.gif" class="Icon"/> 常用工具
					</div>
					<ul class="MenuLevel2" style="display: none">
						<%-- 显示二级菜单 --%>
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://www.hao123.com/haoserver/jishuqii.htm"> 计算器</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://qq.ip138.com/day/"> 万年历</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://ip.chemdrug.com/"> IP查询</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://www.airchina.com.cn/"> 飞机航班</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://www.ip138.com/post/"> 邮编/区号</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://map.haosou.com/"> 地图/公交</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://www.12306.cn/mormhweb/"> 12306铁路</a>
							</div>
						</li>
						
						<li class="level2">
							<div class="level2Style">
								<img src="style/images/MenuIcon/menu_arrow_single.gif" />
								<a target="_blank" href="http://www.showji.com/"> 手机归属地</a>
							</div>
						</li>
						
					</ul> 
				</li>
				
				
			</ul>
		</div>
	</body>
</html>
