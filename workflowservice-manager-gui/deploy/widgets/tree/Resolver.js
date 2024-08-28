/* Copyright (c) Ericsson 2014 */

define('widgets/Tree/tree/Resolver/Resolver',[],function () {

    /**
     * If checked, the parent is checked (and will recursively check the parent's parent).
     * If there's children, each of them is checked, and then each one of them is resolved.
     *
     * @method resolveCheckbox
     * @private
     * @param {Object} item
     */
    function resolveCheckbox(item, isChecked) {
        if (item.checkbox) {
            var allow = isChecked !== undefined || item.checkbox.checked? true : false;
            if (allow) {
                setParentCheckbox(item._parent);
                if (item.children) {
                    setChildrenCheckboxes(item, isChecked);
                }
            }
        }

        if (item.children) {
            item.children.forEach(function(child) {
                resolveCheckbox(child, isChecked);
            });
        }
    }

    /**
     * Iterates over the children of the parent, and checks their checked status and calculates the checked status of the parent.
     * If the passed parent has a parent, the checkbox of that parent will also be modified and updated using the same mechanism.
     *
     * @method setParentCheckbox
     * @private
     * @param {Object} parent
     */
    function setParentCheckbox(parent) {
        if (parent !== undefined) {
            var children = parent.children;
            var unchecked = 0, checked = 0, tripled = 0, checkboxed = 0;

            if (children) {
                children.forEach(function(child) {
                    if (child.checkbox) {
                        checkboxed++;
                        var isChecked = child.checkbox.checked;
                        var isTriple = child.checkbox.triple === true;
                        if (isChecked && !isTriple) {
                            checked++;
                        } else if (isChecked && isTriple) {
                            tripled++;
                        } else {
                            unchecked++;
                        }
                    }
                });
            }

            if (checkboxed > 0) {
                parent.checkbox.triple = false;
                if (unchecked === checkboxed) {
                    parent.checkbox.checked = false;
                } else {
                    parent.checkbox.checked = true;
                    if (checked < checkboxed || tripled > 0) {
                        parent.checkbox.triple = true;
                    }
                }
            }
            setParentCheckbox(parent._parent);
        }
    }

    /**
     * For each child the item has, their checkbox is set. If they have children, they're also checked recursively.
     *
     * @method setChildrenCheckboxes
     * @private
     * @param {Object} item
     */
    function setChildrenCheckboxes (item, isChecked) {
        var checkedValue = isChecked !== undefined? isChecked : true;
        item.children.forEach(function(child) {
            if (child.checkbox && !child.disabled && !child.checkbox.disabled) {
                child.checkbox.checked = checkedValue;
                if (child.children) {
                    setChildrenCheckboxes(child, checkedValue);
                }
            }
        });
    }


    return {
        resolveCheckbox: resolveCheckbox,
        setParentCheckbox:setParentCheckbox,
        setChildrenCheckboxes: setChildrenCheckboxes
    };


});

define('widgets/tree/Resolver',['widgets/Tree/tree/Resolver/Resolver'],function (main) {
                        return main;
                    });

