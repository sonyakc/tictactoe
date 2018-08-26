package com.schhabra.ultimate.tictactoe.service;

import com.schhabra.ultimate.tictactoe.AppConfiguration;
import com.schhabra.ultimate.tictactoe.aspect.AfterAspect;
import com.schhabra.ultimate.tictactoe.aspect.BeforeAspect;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {BeforeAspect.class, AfterAspect.class})
//@ContextConfiguration(classes = AppConfiguration.class)
public class BaseServiceTest {
}
