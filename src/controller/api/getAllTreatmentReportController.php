<?php

class getAllTreatmentReportController extends apiController {
    public function GET () {
        $this->responseJsonData($this->hospitalRepository->getAllTreatmentReport());
    }
}