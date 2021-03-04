package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.exceptions.ResourceNotFoundException;
import com.lambdaschool.anywherefitness.models.Instructor;
import com.lambdaschool.anywherefitness.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
