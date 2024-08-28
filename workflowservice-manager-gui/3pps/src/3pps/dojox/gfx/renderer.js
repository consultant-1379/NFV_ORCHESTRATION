define(["./_base", "3pps/dojox/gfx/svg"],
  function(g, svgRenderer){
  //>> noBuildResolver
	g.renderer = 'svg';
	return svgRenderer;
});
