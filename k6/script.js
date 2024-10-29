import http from 'k6/http';
import { check, group, sleep } from 'k6';

const BASE_URL = 'http://localhost:8080'
//const BASE_URL = 'http://enginebe:8080'

// Global options for tests
export let options = {
    scenarios: {
        performance: {
            executor: 'constant-vus',
            vus: 10, // virtual users
            duration: '120s', 
            exec: 'performanceTest', // defined function
        },
        load: {
            executor: 'ramping-vus',
            startVUs: 10,
            stages: [
                { duration: '30s', target: 10 }, // starting load
                { duration: '30s', target: 30 }, // increase load
                { duration: '30s', target: 0 }, // reduce load
            ],
            exec: 'loadTest', // defined function
        },
        stress: {
            executor: 'constant-arrival-rate', //A fixed number of iterations are executed in a specified period of time.
            rate: 50, // 50 requests per second
            timeUnit: '1s', 
            duration: '30s', // test duration
            preAllocatedVUs: 25, // max virtual user
            exec: 'stressTest', // defined function
        },
        regression: {
            executor: 'per-vu-iterations', //Each VU executes an exact number of iterations.
            vus: 5,
            iterations: 10, // each virtual user will perform 10 iterations
            exec: 'regressionTest', // defined functions
        },
    },
};

export function performanceTest() {
    group('Performance Test', function () {
        testGetProducts();
        testGetRegions();
        testGetRiskProfiles();
        testGetTimeHorizon();
        sleep(1); 
    });
}

export function loadTest() {
    group('Load Test', function () {
        testGetProducts();
        testGetRegions();
        testGetRiskProfiles();
        testGetTimeHorizon();
        sleep(1); 
    });
}

export function stressTest() {
    group('Stress Test', function () {
        testGetProducts();
        testGetRegions();
        testGetRiskProfiles();
        testGetTimeHorizon();
        sleep(1); 
    });
}

export function regressionTest() {
    group('Regression Test', function () {
        testGetProducts();
        testGetRegions();
        testGetRiskProfiles();
        testGetTimeHorizon();
    });
}

function checkResult(res){
    //console.log(`Response for products: ${JSON.stringify(res)}`);
    check(res, {
        'GET - Status is 200': (r) => r.status === 200,
        'GET - Response time < 200ms': (r) => r.timings.duration < 200,
    });
}

function testGetProducts(){
    let res = http.get(`${BASE_URL}/domain/products`);
    checkResult(res)
}

function testGetRegions(){
    let res = http.get(`${BASE_URL}/domain/regions`);
    checkResult(res)
}

function testGetRiskProfiles(){
    let res = http.get(`${BASE_URL}/domain/riskProfiles`);
    checkResult(res)
}

function testGetTimeHorizon(){
    let res = http.get(`${BASE_URL}/domain/timeHorizon`);
    checkResult(res)
}