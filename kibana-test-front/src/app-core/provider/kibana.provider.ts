import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class KibanaProvider {

  private apiUrl = environment.apiUrl + 'kibana/';

  constructor(protected http: HttpClient) {
  }

  getDashboard(): Observable<string> {
    return this.http.get(this.apiUrl + 'dashboard', {responseType: 'text'});
  }

}
