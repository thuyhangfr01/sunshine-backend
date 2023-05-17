package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.config.Config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ute.sunshinebackend.dto.PaymentResDto;
import com.ute.sunshinebackend.dto.TransactionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @GetMapping("/create_payment/{amount}")
    public ResponseEntity<?> createPayment(@PathVariable("amount") long amount) throws UnsupportedEncodingException {
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        PaymentResDto paymentResDto = new PaymentResDto();
        paymentResDto.setStatus("Ok");
        paymentResDto.setMessage("Success");
        paymentResDto.setURL(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(paymentResDto);
    }

    @GetMapping("/payment_info/{vnp_Amount}/{vnp_BankCode}/{vnp_BankTranNo}/{vnp_PayDate}/{vnp_ResponseCode}/{vnp_TransactionNo}")
    public ResponseEntity<?> transaction(
            @PathVariable("vnp_Amount") String amount,
            @PathVariable("vnp_BankCode") String bankCode,
            @PathVariable("vnp_BankTranNo") String bankTranNo,
            @PathVariable("vnp_PayDate") String payDate,
            @PathVariable("vnp_ResponseCode") String responseCode,
            @PathVariable("vnp_TransactionNo") String transaction
        ){
        TransactionDto transactionDto = new TransactionDto();
        if (responseCode.equals("00")) {
            transactionDto.setStatus("Ok");
            transactionDto.setMessage("Successful!");
            transactionDto.setData("");
        } else{
            transactionDto.setStatus("Error");
            transactionDto.setMessage("Failed!");
            transactionDto.setData("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
    }
}
