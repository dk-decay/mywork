System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var EmailValidator;
    return {
        setters:[],
        execute: function() {
            EmailValidator = class EmailValidator {
                static validateEmail(control) {
                    var email = control.value;
                    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    console.log(re.test(email));
                    if (email != null && !re.test(email)) {
                        return { validEmail: true };
                    }
                    return null;
                }
            };
            exports_1("EmailValidator", EmailValidator);
        }
    }
});
//# sourceMappingURL=email.validator.js.map