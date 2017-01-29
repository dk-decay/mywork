System.register(['rxjs/Subject'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Subject_1;
    var QueueService;
    return {
        setters:[
            function (Subject_1_1) {
                Subject_1 = Subject_1_1;
            }],
        execute: function() {
            QueueService = class QueueService {
                constructor() {
                    this.parentQueue = new Subject_1.Subject();
                    this.childQueue = new Subject_1.Subject();
                    this.parentQOb$ = this.parentQueue.asObservable();
                    this.childQOb$ = this.childQueue.asObservable();
                }
                sendParent(str) {
                    this.parentQueue.next(str);
                }
                sendChild(str) {
                    this.childQueue.next(str);
                }
            };
            exports_1("QueueService", QueueService);
        }
    }
});
//# sourceMappingURL=queue.service.js.map