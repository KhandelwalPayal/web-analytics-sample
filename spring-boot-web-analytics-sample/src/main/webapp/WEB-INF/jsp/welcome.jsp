<html>
<head>
	<title>Welcome</title>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
		rel="stylesheet">
	<link href="css/custom.css"
		rel="stylesheet">
		
	 <script type="text/javascript">
      var sp=sp||[];(function(){var e=["init","identify","track","trackLink","pageview"],t=function(e){return function(){sp.push([e].concat(Array.prototype.slice.call(arguments,0)))}};for(var n=0;n<e.length;n++)sp[e[n]]=t(e[n])})(),sp.load=function(e,o){sp._endpoint=e;if(o){sp.init(o)};var t=document.createElement("script");t.type="text/javascript",t.async=!0,t.src=("https:"===document.location.protocol?"https://":"http://")+"d21ey8j28ejz92.cloudfront.net/analytics/v1/sp.min.js";var n=document.getElementsByTagName("script")[0];n.parentNode.insertBefore(t,n)};
      sp.load("http://localhost:8080"); // Data Collection Endpoint
    </script>
</head>
<body>
	<div class="container">
		
		<button type="button" name="button" data-type="A" onclick="">Button A</button>
	    <button type="button" name="button" data-type="B">Button B</button>
	
	    <select id="selector" name="Selector A">
	          <option value="Item 0" selected>Select an item</option>
	          <option value="Item 1">Item 1</option>
	          <option value="Item 2">Item 2</option>
	          <option value="Item 3">Item 3</option>
	    </select>
	    <br>
	    <a href="http://www.jquery.org">Outgoing link A</a>
	    <a href="http://events.jquery.org">Outgoing link B</a>
		
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<!-- 
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
		<script src="js/custom.js"></script>
		
		
	</div>
</body>
</html>
