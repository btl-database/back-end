<?php

class getAllExaminationReportController extends apiController {
    public function GET () {
        $this->responseJsonData($this->hospitalRepository->getAllExaminationReport());
    }
}