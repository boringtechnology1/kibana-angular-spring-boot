import {Component, OnInit} from '@angular/core';
import {KibanaProvider} from "../app-core/provider/kibana.provider";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  kibanaDashboard = '';

  constructor(private kibanaProvider: KibanaProvider) {
  }

  ngOnInit(): void {
    this.kibanaProvider.getDashboard().subscribe(r =>
      this.kibanaDashboard = environment.frontendUrl + r)
  }

  iframeLoaded(e: Event): void {
    const ifr: HTMLIFrameElement = e.target as HTMLIFrameElement;

    if (ifr.contentDocument) {

      const mutationObserver = new MutationObserver(() => {
        if (ifr.contentDocument?.getElementById('dashboardListingHeading')) {
          return;
        }

        // Wait for kibana wrapper to fully load
        const kbnWrapperList = ifr.contentDocument?.getElementsByClassName('kbnAppWrapper');
        if (kbnWrapperList && kbnWrapperList.length > 0) {

          // Set correct kibana iframe height
          ifr.height = kbnWrapperList[0].scrollHeight + 'px';
        }
      });

      const config = {
        subtree: true,
        childList: true,
        characterData: true
      };

      mutationObserver.observe(ifr.contentDocument.body, config);
      setTimeout(() => mutationObserver.disconnect(), 10000);
    }
  }

}
