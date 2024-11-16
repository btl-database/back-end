<?php

class apiController extends Controller {
    public function __construct() {
        parent::__construct();

        $token = null;

        if (isset($headers['Authorization'])) {
            $token = str_replace('Bearer ', '', $headers['Authorization']);
        }

        if($token == null) {
            $token = $_COOKIE['token'];

            if($token == NULL)
                $this->responseJsonData("Api yêu cầu đăng nhập", 401);
        }

        $decoded = jwtService::validateToken($token);

        $_SESSION['username'] = $decoded->sub;
    }
}