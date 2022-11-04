import {NgModule} from '@angular/core';
import {SafeUrlPipe} from "./safe-url.pipe";

const COMPONENTS = [
  SafeUrlPipe
];

@NgModule({
  declarations: [
    ...COMPONENTS,
  ],
  imports: [],
  exports: [
    ...COMPONENTS
  ]
})
export class AppSharedPipesModule {
}
