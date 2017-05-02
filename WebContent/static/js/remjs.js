
	/* rem布局js函数  */
	function remFn(num){
			window.document.documentElement.style.fontSize = document.documentElement.clientWidth/num+'px';
		}
	    remFn(10.8);
	    window.onresize = function(){
	        remFn(10.8);      
	}

