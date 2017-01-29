import {provide} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {AppComponent} from './app.component';
import {ROUTER_PROVIDERS, LocationStrategy, HashLocationStrategy, PathLocationStrategy, APP_BASE_HREF} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';
bootstrap(AppComponent, [ROUTER_PROVIDERS,
            provide(LocationStrategy, {useValue :'/marketplace' }),
            provide(LocationStrategy,
            {useClass: PathLocationStrategy}),
            HTTP_PROVIDERS]);
