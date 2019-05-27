package com.example.rest.service.contact;

import com.example.rest.service.contact.controller.RestContactControllerTest;
import com.example.rest.service.contact.service.impl.DefaultContactCacheServiceTest;
import com.example.rest.service.contact.service.impl.DefaultFilterServiceTest;
import com.example.rest.service.contact.service.impl.DefaultFilteredContactCacheServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RestContactControllerTest.class,
        DefaultFilterServiceTest.class,
        DefaultContactCacheServiceTest.class,
        DefaultFilteredContactCacheServiceTest.class
})
public class RunAllTests {
}
