% The Test Suite for CEC09 Multiobjective Optimization Competitions.
% 
%
% Note that the test problems here includes only UF1-UF10, the three five objective
% problems are tested only in our C++ code cause there is no matlab
% implementation for the three problems.
% 
% the content of this file should be self-explained.
% however, any quesion please contact Wudong Liu (wudong.liu@gmail.com)
%
%

problems = {'uf1','uf2','uf3','uf4','uf5','uf6','uf7','uf8','uf9','uf10'};

plength = length(problems);
%total test run.
totalrun = 30;
%The result IDG Metrics recorded.
DM = zeros(plength, totalrun);

path('cec09',path);
path('moalg',path);

seed = 10;

for i=1:plength
    prob = problems{i};
    disp(sprintf('Running on %s', prob));
    for j = 1:totalrun
        mop = testmop(prob,30);
        pareto = moead(mop,'seed',j+seed);

        objpareto=[pareto.objective];
        %need to filter the result.
        
        %compute the IDG.
        [R, prefix, problemName, dim, tp, objd]=getproblem(prob);
        
        
        S = size(objpareto);
        xtemp = ones(S);
        [CF,x] = ParetoFilter(objpareto,xtemp);
        
        if (objd==2)
            trimnumber=100;
        elseif (objd==3)
            trimnumber=150;
        elseif (objd==5)
            trimnumber=1000;
        end
        
        if (objd>=3)
             boolindex = cec09filter(CF,trimnumber);  
        else
             boolindex = newfilter(CF');
        end
        
        CF = CF(:,boolindex);
        DM(i,j)=DMetric(tp,CF);
        disp(sprintf('The IDG of this run %d is: %f', j, DM(i,j)));
    end
end