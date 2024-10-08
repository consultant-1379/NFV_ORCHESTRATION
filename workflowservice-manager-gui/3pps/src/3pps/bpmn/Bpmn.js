define(['jscore/base/jquery', '3pps/bpmn/Transformer', '3pps/bpmn/Renderer'], function ($, Transformer, Renderer) {

  function Bpmn() { }

  Bpmn.prototype.renderUrl  = function (url, options) {

    var deferred = jQuery.Deferred();
    var self = this;

    $.get(url).done(function(bpmnXml) {
      var result;

      try {
        deferred.resolve(self.render(bpmnXml, options));
      } catch (error) {
        deferred.reject(error);
      }
      
    }).fail(function(error) {
      deferred.reject(error);
    });

    return deferred;
  };
  
  Bpmn.prototype.renderDiagram = function(diagram, options) {
    if (options.xid && options.xid != null) {
        xid = options.xid;
    }

    var definitionRenderer = new Renderer(diagram);
    definitionRenderer.render(options);

    this.definitionRenderer = definitionRenderer;
    this.processDefinitions = diagram;
    this.options = options;

    // zoom the diagram to suite the bounds specified on options if any;
    var bounds = definitionRenderer.getSurface().getDimensions(), bwidth = 1, bheight = 1;

    if (bounds) {
      bwidth = parseFloat(bounds.width),
      bheight = parseFloat(bounds.height);
    }

    var scale = Math.min(
          (options.width || bwidth) / bwidth,
          (options.height || bheight) / bheight);
    
    this.zoom(scale);

    return this;
  };
  
  Bpmn.prototype.render = function(bpmnXml, options) {
    var processDefinition = new Transformer().transform(bpmnXml);
    this.bpmnXml = bpmnXml;
    this.renderDiagram(processDefinition, options);
    return this;
  };

  Bpmn.prototype.zoom = function (factor) {
    var transform = this.definitionRenderer.gfxGroup.getTransform();

    var xx = 1;
    var yy = 1;

    if (!!transform) {
      xx = transform.xx;
      yy = transform.yy;
    }

    this.definitionRenderer.gfxGroup.setTransform({xx:factor, yy:factor});
    var currentDimension = this.definitionRenderer.getSurface().getDimensions();
    this.definitionRenderer.getSurface().setDimensions(+currentDimension.width/xx * factor, +currentDimension.height/xx * factor);

    $.each(this.getOverlays(), function(i, element) {
      element.style.left = element.style.left.split("px")[0]/xx * factor + "px";
      element.style.top = element.style.top.split("px")[0]/yy * factor + "px";
      element.style.width = element.style.width.split("px")[0]/xx * factor + "px";
      element.style.height = element.style.height.split("px")[0]/yy * factor + "px";
    });

    return this;
  };

  Bpmn.prototype.getOverlays = function() {
    // return $("#" + this.options.diagramElement + " .bpmnElement");
    return document.getElementsByClassName(xid+"bpmnElement");
  };

  Bpmn.prototype.getOverlay = function(id) {
    // return $("#" + id);
    return document.getElementsByClassName(xid+id)[0];
  };

  Bpmn.prototype.annotation = function (id) {
    var element = this.getOverlay(id);
    if (!element) {
      throw new Error("Element " + id + " does not exist.");
    }

    return {
      /**
       * adds child annotation div bpmn element div
       * @param innerHTML the inner html of the new annotation
       * @param classesArray classes of the new annotation
       * @returns the DOM element of the new annoation
       */
      // addDiv : function (innerHTML, classesArray) {
      //   return $("<div></div>")
      //             .html(innerHTML)
      //             .addClass((classesArray || []).join(" "))
      //             .appendTo(element);
      // },
      addDiv : function (innerHTML, classesArray) {

        var html = "<div class='" + (classesArray || []).join(" ") + "'>" + innerHTML + "</div>";

        var div = document.createElement("div");
        div.innerHTML = innerHTML;
        div.className = "";
        for (var  i = 0; i < classesArray.length; i++) {
          div.className += " " + classesArray[i];
        }

        return element.appendChild(div);
      },

      /**
       * sets the html of the bpmn element div
       * @param html
       * @returns the annotation builder object
       */
      setHtml : function (html) {
        element.html(html);
        return this;
      },
      /**
       * adds classes to the bpmn element div
       * @param classesArray
       * @returns {*}
       */
      addClasses : function (classesArray) {
        // element.addClass((classesArray || []).join(" "));
        for (var  i = 0; i < classesArray.length; i++) {
          element.className += " " + classesArray[i];
        }
        return this;
      },

      removeClasses : function (classesArray) {
        // element.removeClass((classesArray || []).join(" "));
        var currentClasses = element.className.split(/ +/);
        var newClasses = [];
        for (var i = 0; i < currentClasses.length; i++) {
          var remove = false;
          for (var j = 0; j < classesArray.length; j++) {
            if (currentClasses[i] === classesArray[j]) {
              remove = true;
              break;
            }
          }
          if (remove === false) {
            newClasses.push(currentClasses[i]);
          }
        }
        var newClassName = "";
        for (var  i = 0; i < newClasses.length; i++) {
          if (i > 0) {
            newClassName += " ";  
          }
          newClassName += newClasses[i];
        }
        element.className = newClassName;
        return this;
      }
    };
  };

  Bpmn.prototype.clearAnnotations = function (id, classesArray) {
    var element = this.getOverlay(id);

    element
      .empty()
      .removeClass((classesArray || []).join(" "));

    return element;
  };

  Bpmn.prototype.clear = function () {
    this.definitionRenderer.gfxGroup.destroy();
    // $("#"+this.options.diagramElement).empty();
    $("#"+this.options.diagramElement).empty();
  };

  var xid = "";    

  return Bpmn;
});
