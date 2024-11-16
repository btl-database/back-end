<?php

use Cassandra\Date;

class insertDoctorController extends apiController {
    public function POST () {
        $this->hospitalRepository->insertDoter($this->requestBody);

        $this->responseJsonData("Thêm bác sĩ thành công");
    }
}