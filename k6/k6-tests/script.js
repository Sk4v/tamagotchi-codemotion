/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * OpenAPI spec version: v0
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://github.com/OpenAPITools/openapi-generator
 *
 * Generator version: 7.9.0
 */


import http from "k6/http";
import { group, check, sleep } from "k6";

const BASE_URL = "http://localhost:8080";
// Sleep duration between successive requests.
// You might want to edit the value of this variable or remove calls to the sleep function on the script.
const SLEEP_DURATION = 0.1;
// Global variables should be initialized.

export default function() {
    group("/domain/riskProfiles", () => {

        // Request No. 1: riskProfile
        {
            let url = BASE_URL + `/domain/riskProfiles`;
            let request = http.get(url);

            check(request, {
                "OK": (r) => r.status === 200
            });
        }
    });

    group("/domain/regions", () => {

        // Request No. 1: regions
        {
            let url = BASE_URL + `/domain/regions`;
            let request = http.get(url);

            check(request, {
                "OK": (r) => r.status === 200
            });
        }
    });

    group("/suitability/check", () => {

        // Request No. 1: suitabilityCheck
        {
            let url = BASE_URL + `/suitability/check`;
            // TODO: edit the parameters of the request body.
            let body = {"riskProfile": "string", "timeHorizon": "string", "region": "string"};
            let params = {
                headers: {
                    "Content-Type": "application/json", "Accept": "*/*"
                }
            };
            let request = http.post(url, JSON.stringify(body), params);

            check(request, {
                "OK": (r) => r.status === 200
            });
        }
    });

    group("/domain/products", () => {

        // Request No. 1: products
        {
            let url = BASE_URL + `/domain/products`;
            let request = http.get(url);

            check(request, {
                "OK": (r) => r.status === 200
            });
        }
    });

    group("/domain/timeHorizon", () => {

        // Request No. 1: timeHorizon
        {
            let url = BASE_URL + `/domain/timeHorizon`;
            let request = http.get(url);

            check(request, {
                "OK": (r) => r.status === 200
            });
        }
    });

}
