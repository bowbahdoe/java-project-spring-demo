## To Set Up Project

```bash
bash < <(curl -s  https://raw.githubusercontent.com/bowbahdoe/jresolve-cli/main/install)
```

```shell
jresolve \
    --output-directory scripts/libs \
    --use-module-names \
    --purge-output-directory \
    @scripts/libs.txt
```

```shell
java @project install
```

## Then

To see available commands run

```shell
java @project
```

