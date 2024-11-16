<?php

class insertOutpatientController extends apiController {
    public function POST () {
        $this->hospitalRepository->insertOutpatient($this->requestBody);

        $this->responseJsonData("Thêm bệnh nhân ngoại trú thành công");
    }

}