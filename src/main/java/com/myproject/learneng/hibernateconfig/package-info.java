@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
        @org.hibernate.annotations.Parameter(
                name = "initial_value",
                value = "2"
        )
})

package com.myproject.learneng.hibernateconfig;
