package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.IncomeDto;
import com.mohammad.ExpenseManager.dto.IncomeResponseDto;
import com.mohammad.ExpenseManager.model.Income;
import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserService userService;


    @Override
    public IncomeResponseDto createIncome(IncomeDto incomeDto) {
        User currentUser = userService.getCurrentEntity();

        Income income = new Income();
        income.setTitle(incomeDto.getTitle());
        income.setDate(incomeDto.getDate());
        income.setDescription(incomeDto.getDescription());
        income.setAmount(incomeDto.getAmount());
        income.setUser(currentUser);

        Income saveIncome = incomeRepository.save(income);

        return new IncomeResponseDto(
                saveIncome.getId(),
                saveIncome.getTitle(),
                saveIncome.getAmount(),
                saveIncome.getDate(),
                saveIncome.getDescription()
        );
    }

    @Override
    public List<IncomeResponseDto> getAllIncomeForCurrentUser() {
        User currentUser = userService.getCurrentEntity();

        List<Income> incomeList = incomeRepository.findByUser(currentUser);

        return incomeList.stream().map(
                income -> new IncomeResponseDto(
                        income.getId(),
                        income.getTitle(),
                        income.getAmount(),
                        income.getDate(),
                        income.getDescription()
                )
        ).toList();
    }

    @Override
    public IncomeResponseDto getIncomeById(Long id) {
        User currentUser = userService.getCurrentEntity();

        Income income = incomeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("this id is not existing"));

        if (!income.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to access this income.");
        }
        return new IncomeResponseDto(
                income.getId(),
                income.getTitle(),
                income.getAmount(),
                income.getDate(),
                income.getDescription()
        );
    }

    @Override
    public IncomeResponseDto updateIncome(Long id,IncomeDto incomeDto){
       User currentUser=userService.getCurrentEntity();

       Income income=incomeRepository.findById(id).orElseThrow(
               ()->new RuntimeException("the income is not existing")
       );

       if (!income.getUser().getId().equals(currentUser.getId())){
           throw new RuntimeException("the income ID is not for current user");
       }

       income.setTitle(incomeDto.getTitle());
       income.setAmount(incomeDto.getAmount());
       income.setDate(incomeDto.getDate());
       income.setDescription(incomeDto.getDescription());

       incomeRepository.save(income);

       return new IncomeResponseDto(
               income.getId(),
               income.getTitle(),
               income.getAmount(),
               income.getDate(),
              income.getDescription()
       );
    }
}
