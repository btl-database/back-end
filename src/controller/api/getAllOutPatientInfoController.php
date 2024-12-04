<?php

class getAllOutPatientInfoController extends apiController {
    public function GET () {
        $this->responseJsonData($this->hospitalRepository->getAllOutPatientInfo());
    }
}