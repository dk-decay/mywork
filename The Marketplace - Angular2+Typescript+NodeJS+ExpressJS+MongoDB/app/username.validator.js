System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var UsernameValidator;
    return {
        setters:[],
        execute: function() {
            UsernameValidator = class UsernameValidator {
                static cannotContainSpace(control) {
                    if (control.value && control.value.indexOf('') >= 0) {
                        return { cannotContainSpace: true };
                    }
                    return null;
                }
            };
            exports_1("UsernameValidator", UsernameValidator);
        }
    }
});
//# sourceMappingURL=username.validator.js.map