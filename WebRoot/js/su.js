/**
 * 通用删除
 * @param {Object} url
 * @param {Object} param
 */
var commonDel = function(url,param) {
	if(confirm('确认删除?')) {
		$.post(url,param,function(data) {
			var jsonData = $.parseJSON(data);
				var jsonArray = [];
				for(var key in jsonData) {
					jsonArray.push(jsonData[key]);
				}
				alert(jsonArray.join('\r'));
				if(!!jsonData['success']) {
					location.reload();
				}
		});
	}
}

/**
 * 对con中的key添加strong标识
 * @author su
 * @param {} con
 * @param {} key
 * @return {}
 */
 var keyStrong = function(con, key) {
	var nk = con.indexOf(key);
	var res = "";
	while (nk != -1) {
		res += con.substring(0, nk) + "<strong>" + key + "</strong>";
		con = con.substr(nk + key.length);
		nk = con.indexOf(key);
	}
	res += con;
	return res;
}
/**
 * 控件显示字符长度
 * eg. $(".box4 h2 > a").textLen(30);
 */
$.fn.extend({
	textLen:function(n){
		return this.each(function(){
			if($(this).text().length>n){
				$(this).text($(this).text().substr(0,n-2)+"...");
			}
		});
	}
});

/**
 * 浮点数四舍五入显示
 * src受控数
 * pos显示小数位数
 */
var formatFloat = function(src, pos) { 
	var v = Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
	alert(v); 
}