<?php

class getAllInPatienInfoController extends apiController {
    public function GET() {
        $this->responseJsonData($this->hospitalRepository->getAllInPatienInfo());
    }
}