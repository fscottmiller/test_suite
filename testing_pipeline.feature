should always send email results
    if failed preparing node env's
        why would it fail here?

        
Given pipeline is running
When not enough nodes available **specifics
Then nodes should be provisioned **need more specifics
        
Given pipeline can't access git repository
When new code is checked in
Then the pipeline should fail
And an email should be sent
        
Given a bad config file is checked in
When pipeline runs
Then pipeline should fail
And an email should be sent

Given pipeline is monitoring repository 
When new code is checked in on branch X
Then pipeline should start on branch X

Given code which fails sanity test is checked in
When pipeline runs
Then pipeline should fail
And an email should be sent

Given failing test is checked in with tag X
When pipeline runs
Then pipeline should fail in stage X
And an email should be sent