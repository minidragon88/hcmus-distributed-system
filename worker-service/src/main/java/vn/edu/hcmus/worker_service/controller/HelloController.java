package vn.edu.hcmus.worker_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmus.worker_service.util.AddressUtility;

@RestController("/")
public class HelloController
{
    @Autowired
    private Environment env;
    @GetMapping
    public String helloGradle()
    {
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPP");
        System.out.println(AddressUtility.getServerAddress());
        System.out.println(AddressUtility.getServerPort());
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPP");
        return "Worker!";
    }
}
