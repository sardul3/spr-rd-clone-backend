package com.sagar.sprfullstack.playground;

import com.sagar.sprfullstack.dto.LoginRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(22,44,55,66,77,11);
        list.forEach(li -> System.out.println(li));

        System.out.println("--");

        List<Integer> filteredList = list.stream().filter(li -> li>45).collect(Collectors.toList());
        filteredList.forEach(li -> System.out.println(li));

        System.out.println("--");

        List<Log> logs = new ArrayList<>();
        logs.add(new Log("cannot connet to db", "ERROR"));
        logs.add(new Log("new user registered", "INFO"));
        logs.add(new Log("pw is not secure", "WARN"));
        logs.add(new Log("connection pool is open", "WARN"));
        logs.add(new Log("file send took too long", "WARN"));

        logs.forEach(log -> System.out.println(log));

        System.out.println("--");

        List<Log> filteredLogs = logs.stream().filter(log -> log.getLevel().equals("WARN")).collect(Collectors.toList());
        filteredLogs.forEach(log -> System.out.println(log));

        System.out.println("--");

        Log filterOrNullLogs = logs.stream().filter(log -> log.getLevel().equals("WARN")).findAny().orElse(null);
        System.out.println(filterOrNullLogs);

        System.out.println("--");

        List<String> mappedAndFilteredLogs = logs.stream().filter(log -> {if(log.getLevel().equals("WARN") && log.getMessage().length()>=17) { return true; }return false; }).map(Log::getMessage).collect(Collectors.toList());
        mappedAndFilteredLogs.forEach(mappedAndFilteredLog -> System.out.println(mappedAndFilteredLog));



    }
}
