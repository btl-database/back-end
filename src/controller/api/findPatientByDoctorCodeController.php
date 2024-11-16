<?php

class findPatientByDoctorCodeController extends apiController {
    public function GET () {
        $result = $this->hospitalRepository->findAllPatientTreatedByADoctor($_GET['d_code']);

        $this->responseJsonData($result);
    }
}