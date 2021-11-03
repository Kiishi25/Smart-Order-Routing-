import { ChangeDetectionStrategy, Component } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { FinancialDataService } from "../../services/financial-data.service";

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    providers: [ FinancialDataService ],
    selector: "app-financial-chart-multiple-data",
    styleUrls: ["./financial-chart-multiple-data.component.css"],
    templateUrl: "./financial-chart-multiple-data.component.html"
})
export class FinancialChartMultipleDataComponent {
    public data: any;
    instrumentCode: string;
    constructor(private dataService: FinancialDataService, private _route: ActivatedRoute) {
        this.instrumentCode = this._route.snapshot.paramMap.get('code');
        this.data = [ this.dataService.getDataByInstrument(this.instrumentCode) ];
    }
}
