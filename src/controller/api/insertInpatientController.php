<?php

class insertInpatientController extends apiController {
    public function POST () {
        $this->hospitalRepository->insertInpatient($this->requestBody);

        $this->responseJsonData("Thêm bệnh nhân nội trú thành công");
    }
}